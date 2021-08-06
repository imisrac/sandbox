import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            int myShipCount = in.nextInt(); // the number of remaining ships
            int entityCount = in.nextInt(); // the number of entities (e.g. ships, mines or cannonballs)
            List<Entity> entities = new ArrayList<>();
            for (int i = 0; i < entityCount; i++) {
                Entity e = new Entity(in.nextInt(), EntityTypeEnum.valueOf(in.next())).withX(in.nextInt()).withY(in.nextInt()).withArgs(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
                entities.add(e);
            }
            for (int i = 0; i < myShipCount; i++) {
                String action;
                Optional<Entity> optionalEntity = entities.stream()
                        .filter(entity1 -> EntityTypeEnum.BARREL.equals(entity1.getType()))
                        .max(Comparator.comparing(Entity::getArg1));
                if (optionalEntity.isPresent()) {
                    Entity entity = optionalEntity
                            .get();
                    action = ShipActionEnum.MOVE.getValue() + " " + entity.getX() + " " + entity.getY();
                } else {
                    action = ShipActionEnum.SLOWER.getValue();
                }
                System.out.println(action); // Any valid action, such as "WAIT" or "MOVE x y"
            }
        }
    }

    public enum ShipActionEnum {
        SLOWER("SLOWER"),
        MOVE("MOVE"),
        WAIT("WAIT"),
        FIRE("FIRE");
        private final String value;

        public String getValue() {
            return value;
        }

        ShipActionEnum(String value) {
            this.value = value;
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

        private final int id;
        private final EntityTypeEnum type;
        private int X;
        private int Y;
        private int arg1;
        private int arg2;
        private int arg3;
        private int arg4;

        public Entity(int id, EntityTypeEnum type) {
            this.id = id;
            this.type = type;
        }

        public Entity withX(int X) {
            this.X = X;
            return this;
        }

        public Entity withY(int Y) {
            this.Y = Y;
            return this;
        }

        public Entity withArgs(int arg1, int arg2, int arg3, int arg4) {
            this.arg1 = arg1;
            this.arg2 = arg2;
            this.arg3 = arg3;
            this.arg4 = arg4;
            return this;
        }

        public EntityTypeEnum getType() {
            return type;
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