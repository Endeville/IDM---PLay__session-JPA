package tudelft.wis.idm_tasks.entities;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "play_sessions")
public class PlaySessionEntity extends BaseEntity {
    private Date date;
    @ManyToOne
    private PlayerEntity host;
    @ManyToOne
    private BoardGameEntity game;
    private int playTime;
    @ManyToMany
    private Set<PlayerEntity> players;
    @ManyToOne
    private PlayerEntity winner;

    public PlaySessionEntity(Date date, PlayerEntity host, BoardGameEntity game, int playTime, Set<PlayerEntity> players, PlayerEntity winner) {
        this.date = date;
        this.host = host;
        this.game = game;
        this.playTime = playTime;
        this.players = players;
        this.winner = winner;
    }

    public PlaySessionEntity() {
    }

    public Date getDate() {
        return date;
    }

    public PlaySessionEntity setDate(Date date) {
        this.date = date;
        return this;
    }

    public PlayerEntity getHost() {
        return host;
    }

    public PlaySessionEntity setHost(PlayerEntity host) {
        this.host = host;
        return this;
    }

    public BoardGameEntity getGame() {
        return game;
    }

    public PlaySessionEntity setGame(BoardGameEntity game) {
        this.game = (BoardGameEntity) game;
        return this;
    }

    public PlaySessionEntity setPlayers(Set<PlayerEntity> players) {
        this.players =players.stream()
                .map(p->(PlayerEntity) p)
                .collect(Collectors.toSet());
        return this;
    }

    public int getPlayTime() {
        return playTime;
    }

    public Set<PlayerEntity> getPlayers() {
        return players;
    }

    public PlayerEntity getWinner() {
        return winner;
    }

    public PlaySessionEntity setWinner(PlayerEntity winner) {
        this.winner = (PlayerEntity) winner;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaySessionEntity that = (PlaySessionEntity) o;
        return playTime == that.playTime && Objects.equals(date, that.date) && Objects.equals(host, that.host) && Objects.equals(game, that.game) && Objects.equals(players, that.players) && Objects.equals(winner, that.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, host, game, playTime, players, winner);
    }

    public String toVerboseString() {
        return "PlaySessionEntity{" +
                "date=" + date +
                ", host=" + host +
                ", game=" + game +
                ", playTime=" + playTime +
                ", players=" + players +
                ", winner=" + winner +
                '}';
    }
}
