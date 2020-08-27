package smart.packaging.technology.services.service.impl;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.function.Function;

@Service
@NoArgsConstructor
public class ThreadSafeMapImpl<V, K> {
    /*
    Реализовать потокобезопасный класс содержащий метод Future<V> compute(K k, Function<K, V> f),
который возвращает значение Future<V> по ключу K в соответствии с заданной функцией f вычисляющей
значение V по ключу K.
В случае, если значение K уже встречалось, то значение V не должно вычисляться повторно, а должно браться из кеша.
    * */

    private Map<K, Future<V>> futures = new ConcurrentHashMap<>();
    private Map<K, V> results = new ConcurrentHashMap<>();

    public synchronized Future<V> compute(K k, Function<K, V> f) {
        if(results.containsKey(k)) //если значение уже вычеслено, отдаем
            return CompletableFuture.completedFuture(results.get(k));

        if(futures.containsKey(k)) //если значение еще не вычеслено, оно может вычисляться
            return futures.get(k); //если вычисляется, отдаем фьюч

        //иначе создаем новый фьюч
        Future<V> future = CompletableFuture.supplyAsync(() -> {
            V result = f.apply(k);
            results.put(k, result); //сохраняем вычесленное значение
            futures.remove(k); //удаляем завершенный фьюч
            return result;
        });

        futures.put(k, future); //сохраняем ссылку на него для будущих обращений

        return future;
    }
}