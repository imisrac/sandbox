package codingame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class CodersoftheCaribbeanTest {

    private Player player;
    private List<Player.Entity> entities;

    @BeforeEach
    void setUp() {
        player = new Player();
        final var ownShip = new Player.Entity(1, Player.EntityTypeEnum.SHIP, new Player.Coord(0, 0)).args(0, 0, 100, 1);
        final var enemyShip = new Player.Entity(2, Player.EntityTypeEnum.SHIP, new Player.Coord(10, 10)).args(0, 0, 100, 0);
        entities = new ArrayList<>();
        entities.add(ownShip);
        entities.add(enemyShip);
    }

    @Test
    void singleBarrel() {
        entities.add(new Player.Entity(3, Player.EntityTypeEnum.BARREL, new Player.Coord(10, 10)).args(1, 0, 0, 0));

        var move = player.play(entities);

        assertThat(move, is("MOVE 0 1"));
    }

    @Test
    void twoBarrels() {
        entities.add(new Player.Entity(3, Player.EntityTypeEnum.BARREL, new Player.Coord(10, 10)).args(1, 0, 0, 0));
        entities.add(new Player.Entity(4, Player.EntityTypeEnum.BARREL, new Player.Coord(20, 20)).args(2, 0, 0, 0));

        var move = player.play(entities);

        assertThat(move, is("MOVE 0 1"));
    }

    @Test
    void whichPaysRelativelyMore() {
        entities.add(new Player.Entity(3, Player.EntityTypeEnum.BARREL, new Player.Coord(10, 10)).args(1, 0, 0, 0));
        entities.add(new Player.Entity(4, Player.EntityTypeEnum.BARREL, new Player.Coord(20, 20)).args(10, 0, 0, 0));

        var move = player.play(entities);

        assertThat(move, is("MOVE 0 1"));
    }

    @Test
    void headsUp() {
        var move = player.play(entities);

        assertThat(move, not("SLOWER"));
    }


    //next: avoid mines and dodge block of enemy ships


    @Test
    void avoidMine() {
        entities.add(new Player.Entity(3, Player.EntityTypeEnum.MINE, new Player.Coord(1, 0)));
        entities.add(new Player.Entity(4, Player.EntityTypeEnum.BARREL, new Player.Coord(2, 0)));

        var move = player.play(entities);

        assertThat(move, is("MOVE 0 1"));
    }

    private static class Player {

        public String play(List<Entity> entities) {
            String action;
            final Map<EntityTypeEnum, List<Entity>> entitiesMap = entities.stream()
                    .collect(Collectors.groupingBy(entity -> entity.type));
            if (entitiesMap.containsKey(EntityTypeEnum.BARREL)) {
                action = move(entitiesMap);
            } else {
                final List<Entity> enemyShips = entitiesMap.get(EntityTypeEnum.SHIP).stream()
                        .filter(entity -> 0 == entity.getArg4())
                        .collect(Collectors.toList());
                action = fight(enemyShips);
            }

            return action;
        }

        private String fight(List<Entity> enemyShips) {
            //assume there is one ship
            Entity enemyShip = enemyShips.get(0);
            return "FIRE " + enemyShip.coord.x + " " + enemyShip.coord.y;
        }

        private String move(Map<EntityTypeEnum, List<Entity>> entities) {
            final Entity ownShip = entities.get(EntityTypeEnum.SHIP).stream()
                    .filter(ship -> 1 == ship.getArg4())
                    .findFirst()
                    .orElseThrow();

            Map<Coord, Coord> cameFrom = new HashMap<>();
            cameFrom.put(ownShip.coord, null);

            Coord target = getData(entities, ownShip.coord, cameFrom);

            List<Coord> path = getPath(ownShip.coord, target, cameFrom);

            final int speed = ownShip.arg2;
            return "MOVE " + path.get(Math.floorDiv(speed, 2)).x + " " + path.get(Math.floorDiv(speed, 2)).y;
        }

        private Coord getData(Map<EntityTypeEnum, List<Entity>> entities, Coord target, Map<Coord, Coord> cameFrom) {
            PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparing(node -> node.priority));
            frontier.add(new Node(target, 0));
            Map<Coord, Integer> costSoFar = new HashMap<>();
            costSoFar.put(target, 0);

            while (!frontier.isEmpty()) {
                Coord current = frontier.poll().coord;

                if (isaBarrel(entities, current)) {
                    target = current;
                    break;
                }
                for (Coord coord : neighbors(current)) {
                    int newCost = costSoFar.get(current) + cost(coord, entities);
                    if (!costSoFar.containsKey(coord) || newCost < costSoFar.get(coord)) {
                        costSoFar.put(coord, newCost);
                        frontier.add(new Node(coord, newCost));
                        cameFrom.put(coord, current);
                    }
                }
            }
            return target;
        }

        private List<Coord> getPath(Coord from, Coord to, Map<Coord, Coord> data) {
            Coord current = to;
            List<Coord> path = new ArrayList<>();
            while (!current.equals(from)) {
                path.add(current);
                current = data.get(current);
            }
            Collections.reverse(path);
            return path;
        }

        private boolean isaBarrel(Map<EntityTypeEnum, List<Entity>> entities, Coord current) {
            return entities.get(EntityTypeEnum.BARREL).stream().map(entity -> entity.coord).anyMatch(current::equals);
        }

        private int cost(Coord coord, Map<EntityTypeEnum, List<Entity>> entitiesByGroup) {
            if (entitiesByGroup.getOrDefault(EntityTypeEnum.MINE, Collections.emptyList()).stream().map(entity -> entity.coord).anyMatch(coord::equals)) {
                return 25;
            }
            if (entitiesByGroup.getOrDefault(EntityTypeEnum.SHIP, Collections.emptyList()).stream().map(entity -> entity.coord).anyMatch(coord::equals)) {
                return Integer.MAX_VALUE;
            }
            return 1;
        }

        private List<Coord> neighbors(Coord coord) {
            Coord[][] oddrDirectionDifferences = {{new Coord(+1, 0), new Coord(0, -1), new Coord(-1, -1),
                    new Coord(-1, 0), new Coord(-1, +1), new Coord(0, +1)},
                    {new Coord(+1, 0), new Coord(+1, -1), new Coord(0, -1),
                            new Coord(-1, 0), new Coord(0, +1), new Coord(+1, +1)}};

            int parity = coord.y & 1;
            return IntStream.rangeClosed(0, 5)
                    .boxed()
                    .map(direction -> oddrDirectionDifferences[parity][direction])
                    .map(diff -> new Coord(coord.x + diff.x, coord.y + diff.y))
                    .filter(coord1 -> coord1.x>=0)
                    .filter(coord1 -> coord1.y>=0)
                    .filter(coord1 -> coord1.x<=22)
                    .filter(coord1 -> coord1.y<=20)
                    .collect(Collectors.toList());
        }

        private static class Node {
            private final Coord coord;
            private final int priority;

            public Node(Coord coord, int priority) {
                this.coord = coord;
                this.priority = priority;
            }
        }

        private static class Coord {
            int x;
            int y;

            public Coord(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Coord coord = (Coord) o;
                return x == coord.x && y == coord.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }
        }

        public enum EntityTypeEnum {
            SHIP(),
            BARREL(),
            MINE(),
            CANNONBALL;

            EntityTypeEnum() {
            }
        }

        private static class Entity {
            private final EntityTypeEnum type;
            private final Coord coord;

            private int arg1;
            private int arg2;
            private int arg4;

            public Entity(int id, EntityTypeEnum type, Coord coord) {
                this.type = type;
                this.coord = coord;
            }

            public Entity args(int arg1, int arg2, int arg3, int arg4) {
                this.arg1 = arg1;
                this.arg2 = arg2;
                this.arg4 = arg4;
                return this;
            }

            public EntityTypeEnum getType() {
                return type;
            }

            public int getArg4() {
                return arg4;
            }

        }
    }
}