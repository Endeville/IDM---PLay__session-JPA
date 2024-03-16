package tudelft.wis.idm_tasks.entities;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "players")
public class PlayerEntity extends BaseEntity implements Player {

    private String name;
    private String nickname;

    @ManyToMany
    private Set<BoardGameEntity> gameCollection;

    public PlayerEntity(String name, String nickname, Set<BoardGameEntity> gameCollection) {
        this.name = name;
        this.nickname = nickname;
        this.gameCollection = gameCollection;
    }

    public PlayerEntity() {
    }

    public String getName() {
        return name;
    }

    public PlayerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public PlayerEntity setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    @Override
    public String getPlayerName() {
        return this.name;
    }

    @Override
    public String getPlayerNickName() {
        return this.nickname;
    }

    public Set<BoardGameEntity> getGameCollection() {
        return gameCollection;
    }

    @Override
    public String toVerboseString() {
        return "PlayerEntity{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gameCollection=" + gameCollection +
                '}';
    }

    public PlayerEntity setGameCollection(Set<BoardGameEntity> gameCollection) {
        this.gameCollection = gameCollection;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(nickname, that.nickname) && Objects.equals(gameCollection, that.gameCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nickname, gameCollection);
    }
}
