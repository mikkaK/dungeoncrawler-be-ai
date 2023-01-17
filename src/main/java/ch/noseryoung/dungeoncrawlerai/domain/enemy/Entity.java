package ch.noseryoung.dungeoncrawlerai.domain.enemy;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@Document(collection = "entities")
public class Entity {
    @Id
    private Integer id;

    @Field
    private String name;

    @Field
    private Integer health;

    @Field
    private Integer damage;

    @Field
    private Integer resistance;

    @Field
    private Integer maxHealth;

    @Field
    private Integer flatPen;

    @Field
    private Integer percentagePen;

    @Field
    private boolean alive;

    public Entity(Integer id, String name, Integer health, Integer damage, Integer resistance, Integer maxHealth, Integer flatPen, Integer percentagePen, boolean alive) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.resistance = resistance;
        this.maxHealth = maxHealth;
        this.flatPen = flatPen;
        this.percentagePen = percentagePen;
        this.alive = alive;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", resistance=" + resistance +
                ", maxHealth=" + maxHealth +
                ", flatPen=" + flatPen +
                ", percentagePen=" + percentagePen +
                ", alive=" + alive +
                '}';
    }
}
