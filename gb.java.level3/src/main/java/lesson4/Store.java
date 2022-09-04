package lesson4;

public class Store {
    private int product = 0;

    public synchronized void getProduct() throws InterruptedException {
        if (product <= 0) {
            wait();
        }
        product--;
        System.out.println("Покупатель купил 1 товар.");
        System.out.println("Всего товаров осталось: " + product);

        notify();
    }

    public synchronized void setProduct() throws InterruptedException {
        if (product > 3) {
            wait();
        }
        product++;
        System.out.println("Продавец пополнил запасы на 1 товар.");
        System.out.println("Всего товаров осталось: " + product);

        notify();
    }

}

class MainStore {
    public static void main(String[] args) {
        Store store = new Store();
        //покупатель
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    store.setProduct();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        //продавец
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    store.getProduct();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
