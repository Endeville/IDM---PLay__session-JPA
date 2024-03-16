package tudelft.wis.idm_tasks.entities;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "board_games")
public class BoardGameEntity extends BaseEntity {
    @Column
    private String name;
    @Column
    private String bggUrl;

    public BoardGameEntity(String name, String bggUrl) {
        this.name = name;
        this.bggUrl = bggUrl;
    }

    public BoardGameEntity() {
    }

    public String getName() {
        return name;
    }

    public BoardGameEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BoardGameEntity setBggUrl(String bggUrl) {
        this.bggUrl = bggUrl;
        return this;
    }

    public String getBggUrl() {
        return bggUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGameEntity boardGame = (BoardGameEntity) o;
        return Objects.equals(name, boardGame.name) && Objects.equals(bggUrl, boardGame.bggUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bggUrl);
    }
}
