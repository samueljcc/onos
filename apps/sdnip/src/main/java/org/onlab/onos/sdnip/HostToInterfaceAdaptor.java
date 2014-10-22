package org.onlab.onos.sdnip;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import org.apache.commons.lang.NotImplementedException;
import org.onlab.onos.net.ConnectPoint;
import org.onlab.onos.net.host.HostService;
import org.onlab.onos.net.host.PortAddresses;
import org.onlab.onos.sdnip.config.Interface;
import org.onlab.packet.IpAddress;

import com.google.common.collect.Sets;



/**
 * Provides IntefaceService using PortAddresses data from the HostService.
 */
public class HostToInterfaceAdaptor implements InterfaceService {

    private final HostService hostService;

    public HostToInterfaceAdaptor(HostService hostService) {
        this.hostService = checkNotNull(hostService);
    }

    @Override
    public Set<Interface> getInterfaces() {
        Set<PortAddresses> addresses = hostService.getAddressBindings();
        Set<Interface> interfaces = Sets.newHashSetWithExpectedSize(addresses.size());
        for (PortAddresses a : addresses) {
            interfaces.add(new Interface(a));
        }
        return interfaces;
    }

    @Override
    public Interface getInterface(ConnectPoint connectPoint) {
        checkNotNull(connectPoint);

        PortAddresses portAddresses =
                hostService.getAddressBindingsForPort(connectPoint);

        if (!portAddresses.ips().isEmpty()) {
            return new Interface(portAddresses);
        }

        return null;
    }

    @Override
    public Interface getMatchingInterface(IpAddress ipAddress) {
        // TODO implement
        throw new NotImplementedException("getMatchingInteface is not yet implemented");
    }

}