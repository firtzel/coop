package models;

import play.data.validation.Required;
import siena.*;

@Table("coop_group")
public class CoopGroup extends Model {

    @Id(Generator.AUTO_INCREMENT)
    public Long id;

    @Column("title")
    @Required @Max(50) @NotNull
	public String title;

    @Column("description")
    @Required @Max(500) @NotNull
	public String description;

    public CoopGroup(String title, String description) {
		this.title = title;
		this.description = description;
	}

    @Override
    public String toString() {
    	return title;
    }
}
