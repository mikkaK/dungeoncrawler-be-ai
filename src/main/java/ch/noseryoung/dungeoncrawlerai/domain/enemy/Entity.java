package ch.noseryoung.dungeoncrawlerai.domain.enemy;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * This is the entity of an Enemy in our database, it's used to update an enemy after balancing logic.
 */
@Data
@NoArgsConstructor
@Document(collection = "entities")
public class Entity {
    @Id
    private String id;

    @Field
    private String name;

    @Field
    private Double health;

    @Field
    private Double damage;

    @Field
    private Double resistance;

    @Field
    private Double maxHealth;

    @Field
    private Integer flatPen;

    @Field
    private Integer percentagePen;

    @Field
    private boolean alive;

    public Entity(String id, String name, Double health, Double damage, Double resistance, Double maxHealth, Integer flatPen, Integer percentagePen, boolean alive) {
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
