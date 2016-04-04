package uk.co.adamgrant.cs4099;

/**
 * Class to store data for a single Lock/Unlock action
 */
public class LockDataEntry {
    // 0 = locked, 1 = unlocked
    private int locked;
    private long epoch;

    /**
     * Constructor initialising values of locked and epoch.
     * @param locked Integer representing locked status of entry.
     *               1 represents unlocked, 0 locked.
     * @param epoch long representing the date/time of entry
     */
    public LockDataEntry(int locked, long epoch) {
        this.locked = locked;
        this.epoch = epoch;
    }

    /**
     * Getter for the locked value. 1 represents unlocked, 0 locked.
     * @return value of locked
     */
    public int getLocked() {
        return locked;
    }

    /**
     * Returns boolean representing whether the current entry is
     * for a Locked action, or Unlocked action.
     *
     * @return whether action refers to locked or unlocked.
     */
    public boolean isLocked() {
        return locked == 0;
    }

    /**
     * Returns value of date/time represented as Epoch
     * @return long value of date/time as Epoch
     */
    public long getEpoch() {
        return epoch;
    }
}
