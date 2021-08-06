package codingame;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CodersoftheCaribbeanTest {

    @Test
    void singleBarrel() {
        Player player = new Player(1);
        String move = player.play(createEntities(1));

        assertThat(move, is("MOVE 10 10"));
    }

    @Test
    void twoBarrels() {
        Player player = new Player(1);
        String move = player.play(createEntities(2));

        assertThat(move, is("MOVE 10 10"));
    }

    private List<Entity> createEntities(int amount) {
        List<Entity> entities = new ArrayList<>();
        new Entity(1, "BARREL").withX(10).withY(10).arg1(1);
        return entities;
    }

    private static class Player {
        private final int myShipCount;

        public Player(int myShipCount) {
            this.myShipCount = myShipCount;
        }

        public String play(List<Entity> entities) {
            String action = "";
            Optional<Entity> optionalEntity = entities.stream()
                    .filter(entity -> "BARREL".equals(entity.type))
                    .findFirst();
            if (optionalEntity.isPresent()) {
                final Entity entity = optionalEntity.get();
                action = "MOVE "+ entity.getX()+" "+entity.getY();
            }

            return action;
        }
    }

    private static class Entity {
        private int id;
        private String type;
        private int X;
        private int Y;

        private int arg1;

        public Entity(int id, String type) {
            this.id = id;
            this.type = type;
        }

        public Entity withX(int x) {
            X = x;
            return this;
        }

        public Entity withY(int y) {
            Y = y;
            return this;
        }

        public Entity arg1(int arg) {
            arg1 = arg;
            return this;
        }

        public int getX() {
            return X;
        }

        public int getY() {
            return Y;
        }

        public int getArg1() {
            return arg1;
        }
    }
}

//    private static class Player {
//
//        private void run() {
//
//            while (true) {
//                int myShipCount = in.nextInt(); // the number of remaining ships
//                int entityCount = in.nextInt(); // the number of entities (e.g. ships, mines or cannonballs)
//                List<Player.Entity> entities = new ArrayList<>();
//                for (int i = 0; i < entityCount; i++) {
//                    Player.Entity e = new Player.Entity(in.nextInt(), Player.EntityTypeEnum.valueOf(in.next())).withX(in.nextInt()).withY(in.nextInt()).withArgs(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
//                    entities.add(e);
//                }
//                for (int i = 0; i < myShipCount; i++) {
//                    String action;
//                    Optional<Player.Entity> optionalEntity = entities.stream()
//                            .filter(entity1 -> Player.EntityTypeEnum.BARREL.equals(entity1.getType()))
//                            .max(Comparator.comparing(Player.Entity::getArg1));
//                    if (optionalEntity.isPresent()) {
//                        Player.Entity entity = optionalEntity
//                                .get();
//                        action = Player.ShipActionEnum.MOVE.getValue() + " " + entity.getX() + " " + entity.getY();
//                    } else {
//                        action = Player.ShipActionEnum.SLOWER.getValue();
//                    }
//                    System.out.println(action); // Any valid action, such as "WAIT" or "MOVE x y"
//                }
//            }
//        }
//
//public enum ShipActionEnum {
//    SLOWER("SLOWER"),
//    MOVE("MOVE"),
//    WAIT("WAIT"),
//    FIRE("FIRE");
//    private final String value;
//
//    public String getValue() {
//        return value;
//    }
//
//    ShipActionEnum(String value) {
//        this.value = value;
//    }
//}
//
//public enum EntityTypeEnum {
//    SHIP(),
//    BARREL(),
//    MINE(),
//    CANNONBALL;
//
//    EntityTypeEnum() {
//    }
//}
//
//private static class Entity {
//
//    private final int id;
//    private final Player.EntityTypeEnum type;
//    private int X;
//    private int Y;
//    private int arg1;
//    private int arg2;
//    private int arg3;
//    private int arg4;
//
//    public Entity(int id, Player.EntityTypeEnum type) {
//        this.id = id;
//        this.type = type;
//    }
//
//    public Player.Entity withX(int X) {
//        this.X = X;
//        return this;
//    }
//
//    public Player.Entity withY(int Y) {
//        this.Y = Y;
//        return this;
//    }
//
//    public Player.Entity withArgs(int arg1, int arg2, int arg3, int arg4) {
//        this.arg1 = arg1;
//        this.arg2 = arg2;
//        this.arg3 = arg3;
//        this.arg4 = arg4;
//        return this;
//    }
//
//    public Player.EntityTypeEnum getType() {
//        return type;
//    }
//
//    public int getX() {
//        return X;
//    }
//
//    public int getY() {
//        return Y;
//    }
//
//    public int getArg1() {
//        return arg1;
//    }
//}