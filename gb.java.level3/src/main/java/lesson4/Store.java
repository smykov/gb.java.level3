package lesson4;

public class Store {
    private int product = 0;

    public void getProduct() {
        synchronized (this) {
            product--;
            System.out.println("Покупатель купил 1 товар.");
            System.out.println("Всего товаров осталось: " + product);
        }
    }

    public void setProduct() {
        synchronized (this) {
            product++;
            System.out.println("Продавец пополнил запасы на 1 товар.");
            System.out.println("Всего товаров осталось: " + product);
        }
    }

}

class MainStore {
    public static void main(String[] args) {
        Store store = new Store();
        //покупатель
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                store.setProduct();
            }
        }).start();
        //продавец
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                store.getProduct();
            }
        }).start();
    }
}
