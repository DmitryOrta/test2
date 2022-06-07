public class Main {
    public static void main(String[] args) {
        SelfDriveCar newAgeCar = new SelfDriveCar();
        newAgeCar.drive();
    }
}

class SelfDriveCar {
    Semaphore semaphore = new Semaphore();

    public void drive() {
        while (!checkEndOfRoute()) {
            SemaphoreLight currentLight = semaphore.getCurrentLight();
            switch (currentLight) {
                case Red -> { System.out.print("Red: "); stop(); }
                case Yellow -> { System.out.print("Yellow: "); waiting(); }
                case Green -> { System.out.print("Green: "); move(); }
            }
        }
        System.out.println("Конец маршрута - приехали");
    }

    boolean checkEndOfRoute() {
        return Math.random() > 0.98;
    }

    void move() { System.out.println("ехать"); }
    void stop() { System.out.println("остановиться"); }
    void waiting() { System.out.println("подождать"); }
}

class Semaphore {
    public SemaphoreLight getCurrentLight() {
        double rand = Math.random();
        if (rand < 0.2) return SemaphoreLight.Yellow;
        if (rand < 0.7) return SemaphoreLight.Green;
        return SemaphoreLight.Red;
    }
}

enum SemaphoreLight {
    Red,
    Yellow,
    Green
}