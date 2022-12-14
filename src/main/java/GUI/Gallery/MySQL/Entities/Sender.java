package GUI.Gallery.MySQL.Entities;

import javax.persistence.*;

@Entity
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sender", nullable = false)
    private int idSender;
    @Column(name = "mail", nullable = false)
    private String mail;
    @Column(name = "path", nullable = false)
    private String path;
    @Column(name = "status", nullable = true)
    private String status;
    @ManyToOne(cascade = CascadeType.ALL)
    private Event event;

    public int getIdSender() {
        return idSender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Event getEvents() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
