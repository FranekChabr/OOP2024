# Tworzenie Wątków

### Dziedziczenie po klasie Thread
```Java
class MyThread extends Thread {
    public void run() {
        // Kod wykonywany przez wątek
    }
}

MyThread t = new MyThread();
t.start(); // Uruchomienie wątku
```

### Implementacja interfejsu Runnable
```Java
class MyRunnable implements Runnable {
    public void run() {
        // Kod wykonywany przez wątek
    }
}

Thread t = new Thread(new MyRunnable());
t.start(); // Uruchomienie wątku
```
Implementacja Runnable jest bardziej zalecana, ponieważ pozwala na dziedziczenie z innej klasy, jeśli jest to konieczne.

# Metody związane z wątkami
- start() – Uruchamia wątek i wywołuje metodę run().
- run() – Metoda, która zawiera kod, który będzie wykonywany przez wątek.
- sleep(long millis) – Wstrzymuje wątek na określoną liczbę milisekund.
- join() – Czeka na zakończenie innego wątku przed kontynuowaniem bieżącego.
- interrupt() – Służy do przerwania wątku.
- isAlive() – Sprawdza, czy wątek jest wciąż aktywny.

# Synchronizacja
- Synchronizacja bloków kodu: Zapewnia, że określony blok kodu może być wykonywany tylko przez jeden wątek w danym momencie. Używa się słowa kluczowego synchronized.
```Java
synchronized(obj) {
    // Kod synchronizowany
}
```
- Synchronizacja metod: Metoda może być oznaczona jako synchronized, co zapewnia, że tylko jeden wątek naraz może ją wykonywać na danym obiekcie.
```Java
synchronized void myMethod() {
    // Kod synchronizowany
}
```
Synchronizacja jest kluczowa, gdy różne wątki mają dostęp do wspólnych zasobów (np. zmiennych obiektów), aby zapobiec problemom takim jak race condition.
