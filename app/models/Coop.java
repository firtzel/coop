package models;

import siena.*;

@Table("coops")
public class Coop extends Model {

    @Id(Generator.AUTO_INCREMENT)
    public Long id;

    @Column("title")
    @Max(50) @NotNull
	public String title;

    @Column("description")
    @Max(500)
	public String description;

	public Coop(String title, String description) {
		this.title = title;
		this.description = description;
	}

    public static Query<Coop> all() {
    	return Model.all(Coop.class);
    }

    @Override
    public String toString() {
    	return title;
    }
}
