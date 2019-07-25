package full_version;

import java.util.Comparator;

public class ComparatorKlient implements Comparator<Record>
{
    //sprawdzić czy rosnaco
    @Override
    public int compare(Record p1, Record p2) {
        if(p2 == null) return -1;
        if(p1.getKlient_id() > p2.getKlient_id()) return 1;
        else if(p1.getKlient_id() < p2. getKlient_id()) return -1;
        else return 0;
    }
}
