package GUI.Gallery.MySQL.Entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id_company", nullable = false)
    private int idCompany;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private final List<Event> event = new ArrayList<>();
    public List<Event> getEvents() {
        return event;
    }
    public int getIdCompany() {
        return idCompany;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
