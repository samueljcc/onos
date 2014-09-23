package org.onlab.onos.net.flow;

import org.onlab.onos.net.DeviceId;

/**
 * Manages inventory of flow rules; not intended for direct use.
 */
public interface FlowRuleStore {

    /**
     * Returns the flow entries associated with a device.
     *
     * @param deviceId the device ID
     * @return the flow entries
     */
    Iterable<FlowRule> getFlowEntries(DeviceId deviceId);

    /**
     * Stores a new flow rule without generating events.
     *
     * @param rule the flow rule to add
     */
    void storeFlowRule(FlowRule rule);

    /**
     * Deletes a flow rule without generating events.
     *
     * @param rule the flow rule to delete
     */
    void deleteFlowRule(FlowRule rule);

    /**
     * Stores a new flow rule, or updates an existing entry.
     *
     * @param rule the flow rule to add or update
     * @return flow_added event, or null if just an update
     */
    FlowRuleEvent addOrUpdateFlowRule(FlowRule rule);

    /**
     * @param rule the flow rule to remove
     * @return flow_removed event, or null if nothing removed
     */
    FlowRuleEvent removeFlowRule(FlowRule rule);

}