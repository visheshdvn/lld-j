package balanceSheet;

import users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceSheet {
    public static class BalanceSheetRecord {
        Float owe;
        Float receive;

        public BalanceSheetRecord(Float owe, Float receive) {
            this.owe = owe;
            this.receive = receive;
        }

        public Float getOwe() {
            return owe;
        }

        public void setOwe(Float owe) {
            this.owe = owe;
        }

        public Float getReceive() {
            return receive;
        }

        public void setReceive(Float receive) {
            this.receive = receive;
        }
    }

    private Float totalGet = 0F;
    private Float totalOwes = 0F;
    private final Map<User, List<BalanceSheetRecord>> recordWithUser;

    public BalanceSheet() {
        this.recordWithUser = new HashMap<>();
    }

    /*
    add to balance Sheet
     */
    public void addToBalanceSheet(User againtUser, BalanceSheetRecord balanceSheetRecord) {
        List<BalanceSheetRecord> userRecords = recordWithUser.getOrDefault(againtUser, new ArrayList<>());
        userRecords.add(balanceSheetRecord);

        Float owes = balanceSheetRecord.getOwe();
        Float gets = balanceSheetRecord.getReceive();

        if(owes != null) {
            totalOwes+=owes;
        }

        if(gets != null) {
            totalGet += gets;
        }

        recordWithUser.put(againtUser, userRecords);
    }

    /*
    getter & setter
     */
    public Float getTotalGet() {
        return totalGet;
    }

    public Float getTotalOwes() {
        return totalOwes;
    }

    public Map<User, List<BalanceSheetRecord>> getRecordWithUser() {
        return recordWithUser;
    }
}
