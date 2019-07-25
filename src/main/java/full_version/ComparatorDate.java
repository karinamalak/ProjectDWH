package full_version;

import java.util.Comparator;

public class ComparatorDate implements Comparator<Record>
{
    @Override
    public int compare(Record p1, Record p2) {
        if(p2 == null) return -1;
        if(p1.getKontakt_ts().after(p2.getKontakt_ts())==true ) return 1;
        else if(p1.getKontakt_ts().before(p2.getKontakt_ts())==true) return -1;
        else return 0;
    }
}


