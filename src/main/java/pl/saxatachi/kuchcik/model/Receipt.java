package pl.saxatachi.kuchcik.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="content")
    private String content;
    @OneToMany
    @JoinColumn(name="receiptId",updatable = false,insertable = false)
    private List<ElementofReceipt> elementofreceipts;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ElementofReceipt> getElementofreceipts() {
        return elementofreceipts;
    }

    public void setElementofreceipts(List<ElementofReceipt> elementofreceipts) {
        this.elementofreceipts = elementofreceipts;
    }
}

