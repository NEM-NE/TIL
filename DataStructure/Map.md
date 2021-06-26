Map
===

## Map이란?

Map은 기존의 JFC와는 다르게 Key, Value가 존재한다.

Key는 중복을 허용하지 않는 ID값이며, Value는 중복을 허용하는 Data 값이다.

또한 기본적으로 Map은 순서를 유지하고 있지 않는다.

가장 대표적인 Map의 예시로는 주민등록번호, ID/PW 이다.

Map이라는 인터페이스를 통해 자바에서는 4가지의 Map이 있다.

1. Hash 알고리즘을 이용한 HashMap
2. HashMap의 이전 버전인 HashTable(Vector와 ArrayList 같은 관계)
3. 이진 탐색 트리를 사용하여 키를 오름차순 정렬까지 해주는 TreeMap
4.  Map에 순서를 부여해주는 LinkedHashMap 

## 왜 Map을 사용해야하나?

Map의 특징은 Key, Value로 나뉘고 순서가 없고 Key는 중복을 허용하지 않는다는 것이다.

따라서 비록 위 특징을 List나 다른 자료구조를 통해 커버할 수 있지만 명백한 "속도" 차이가 나기 때문에 Map을 사용하는 것이 좋다.

Map을 사용해야 되는 경우는 다음과 같다.

1. 특정 데이터를 순간마다 캐치해야 할 때
2. 특정 품목의 갯수를 카운트 해야할 때
3. 저장하고 싶은 데이터가 특별한 Key 값을 가질 때

## Map의 동작원리

Map 기반 클래스들은 Set 기반 클래스들과 비슷한 동작원리를 가진다.

Map의 특징은 Key, Value를 저장하는 Entry 객체를 내부 클래스로 만들고 Entry 배열을 버킷으로 사용한다는 점이 특징이다.

Entry 클래스 hash, key, value, 다음 Node 값을 가지고 있다.

```java

   static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }


```

## Map의 주요 기능

Map의 기본 기능은 다음과 같다.

![맵 기본 메서드](./img/map.png)

CRUD 기능인 put(), remove(), get() 이외에도

containsKey(), containsValue(), entrySet(), keySet(), getOrDefault(), replace()이 자주 사용된다.

### 추가

Map에 저장 된 Value를 꺼내려면 Key를 알아야한다. 그러려면 따로 Key를 저장할 배열이나 List를 만들고 넣어줘야하기 때문에 코드가 복잡해 질 수 있다.

위 같은 상황일 때 map.entrySet() 함수를 사용하여 Set에 넣고 다시 하나씩 빼서 value를 구하는 것이다.

<strong>여기서 주의할 점은 set에서 나오는 클래스는 Map.Entry로 주의해야한다.</strong>

```java

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, String> map  = new HashMap<String, String>();
		
		map.put("990727", "SungBin");
		map.put("123456", "Test");
		map.put("000607", "TTT");
		map.put("412434", "TTTTTTT");
		
		Set set = map.entrySet();
		
        //1번

		LinkedList list = new LinkedList(set);
		
		for(int i = 0; i < list.size(); i++) {
            //list에서 나오는 클래스는 Map.Entry이다.
			Map.Entry<String, String> entry = (Map.Entry<String, String>) list.get(i);
			System.out.println(entry.getValue());
		}
		
        //2번

        Iterator it = set.iterator();

		while(it.hasNext()) {
            //it.next()에서 나오는 클래스는 Map.Entry이다.
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			System.out.println(entry.getValue());
		}
	
	}

```

