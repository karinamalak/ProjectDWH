package full_version;

import java.util.Date;

public class Record {
    private int kontakt_id;
    private int klient_id;
    private int pracownik_id;
    private String status;
    private Date kontakt_ts;

    public Record(int kontakt_id, int klient_id, int pracownik_id, String status, Date kontakt_ts) {
        this.kontakt_id = kontakt_id;
        this.klient_id = klient_id;
        this.pracownik_id = pracownik_id;
        this.status = status;
        this.kontakt_ts=kontakt_ts;
    }

    public int getKontakt_id() {
        return kontakt_id;
    }

    public int getKlient_id() {
        return klient_id;
    }

    public int getPracownik_id() {
        return pracownik_id;
    }

    public String getStatus() {
        return status;
    }

    public Date getKontakt_ts() {
        return kontakt_ts;
    }

    @Override
    public String toString() {
        return "Record{" +
                "kontakt_id=" + kontakt_id +
                ", klient_id=" + klient_id +
                ", pracownik_id=" + pracownik_id +
                ", status='" + status + '\'' +
                ", kontakt_ts=" + kontakt_ts +
                '}';
    }
}
