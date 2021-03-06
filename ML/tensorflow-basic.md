# tensorflow 개론

머신러닝의 종류는 지도 학습, 비지도 학습, 강화 학습으로 나뉘어진다.

이번 시간에 해결할 문제는 지도 학습에서의 회귀와 분류를 통해 해결 할 것이다.

회귀와 분류를 사용하여 풀기 위해서는 여러 알고리즘을 통해 사용하는데

대표적으로 `Decision Tree`, `Random Forest`, `KNN`, `SVM`, `Neural Network(Deep Learning)` 등이 존재한다.

이러한 알고리즘을 사용하기 쉽게 변형한 것이 라이브러리이고 대표적으로 Tensorflow, PyTorch, Caffe2, theano 등이 존재한다.

지도학습 과정

1. 과거의 데이터를 준비한다.
2. 데이터에서 원인(독립변수)과 결과(종속변수)를 찾는다.
3. 모델의 구조를 만든다.
4. 데이터로 모델을 학습(FIT)한다.
5. 모델을 이용한다. 

주피터 노트북 → 노트북이라고 불리는 파일을 웹브라우저에서 실행하여 쉽게 이용

데이터를 준비하고 원인과 결과를 분리해야한다.

여기서 데이터는 CSV 파일 형태로 제공하고 그 안에서 원인과 결과를 분리해야한다.

python에서는 이러한 테이블 형태의 데이터를 조작할 수 있도록 pandas라는 라이브러리를 제공하고 있다.

pandas는 read_csv라는 메서드를 통해 csv를 읽어서 DataFrame이라는 클래스 형태로 저장을 한다.

여기서 DataFrame.shape라는 메서드를 통해 컬럼과 로우에 대한 정보를 얻을 수 있으며 DataFrame.columns라는 메서드를 통해 컬럼에 대한 이름을 알 수 있다. 이를 통해 변수를 추출할 수 있다.

전체 개요

![스크린샷 2022-05-03 오후 10.48.50.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/32f44ce7-1fb2-48b7-aec8-eff7d1175c4f/스크린샷_2022-05-03_오후_10.48.50.png)

독립 변수와 종속 변수의 개수를 파악하여 2번 모델의 구조를 만들 때 반영한다.

3번에서는 몇 번 학습할 것인지 지정한다.

### Loss

Loss ⇒ 학습이 얼마나 되었는지 알려주는 척도

![스크린샷 2022-05-03 오후 10.50.52.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0aed9aaf-4f96-433d-9e93-a1d5947e9d58/스크린샷_2022-05-03_오후_10.50.52.png)

Loss는 (예측 - 결과)$^2$ 의 평균 값이다. 즉, loss가 0에 가까울 수록 학습이 잘 되었다를 알 수 있다.

2번의 구조를 더 세부적으로 보면 다음과 같다. 구조를 만든다는 것은 수식을 만든다는 것과 같다. 즉, Input으로 입력된 독립변수에 가중치를 곱하고 편향(bias)값을 더 함으로써 완성 시킬 수 있다.

y = w1x1 + w2x2 + w3x3 + ... + b 와 같이 만들게 된다. 이를 수식 다른 말로 퍼셉트론이라고 한다.

만약에 종속변수가 2개라고 하면 퍼셉트론이 2개가 필요하게 될 것이다.

### 분류일 경우

![스크린샷 2022-05-04 오전 12.53.53.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a3b3d144-89f7-4359-bc5e-8dd3e9a22268/스크린샷_2022-05-04_오전_12.53.53.png)

기존에 회귀일때와는 몇가지 부분이 달라졌다.

회귀는 종속변수가 양적인 데이터일 때 사용 가능하다. 즉, y = w1x1 + w2x2 + w3x3 + .. + b인 데이터를 통해 종속변수를 구할 수 있다.

그러나 범주형 데이터가 종속변수인 회귀에서는 이러한 특정 수치를 구하는 수식을 사용하기가 어렵다.

범주형 데이터를 수치화 시켜주는 `원핫인코딩` 이라는 과정을 필수적으로 거쳐야한다.

![스크린샷 2022-05-04 오전 12.59.20.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/86587d83-dab1-41c8-a0d4-7db8b016a8de/스크린샷_2022-05-04_오전_12.59.20.png)

원핫인코딩 과정을 통해 y = w1x1 + w2x2 + w3x3 + .. + b 같은 수식을 만들어 setosa인지, virginica, versicolor인지를 판단할 수 있다.

### 분류에서의 activation, loss

2번 코드를 보면 activation 설정과 loss 설정이 변경되었음을 알 수 있다.

분류는 A일 확률이 ~~%야 라고 말한다.(정확한 분류는 어렵기 때문에)

즉, 확률의 개념으로 분류의 결과를 나타내야한다. 이를 가능하게 해주는 것이 softmax 함수

또한 y = w1x1 + w2x2 + w3x3 + .. + b  수식의 값은 -무한대 ~ 무한대가 될 수 있다.

그러나 확률을 나타내기 위해서는 0 ~ 1의 값만이 필요하다. 즉, softmax 함수가 이를 가능하게 한다.

![스크린샷 2022-05-04 오전 1.08.09.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/63dd8b8b-7597-498d-855e-afcdc09e4e04/스크린샷_2022-05-04_오전_1.08.09.png)

위 사진은 퍼셉트론의 구조이며 최종적으로 f 함수를 거친다. 회귀 모델은 identity를 분류모델은 softmax를 거친다. 이렇게 퍼셉트론의 출력이 어떤 형태로 나아가야하는지 결정하는 함수를 `활성화 함수`라고 한다.

그 다음으로 Loss는 값이 낮을 수록 모델의 정확도가 높다고 했다. 여기서 학습이 제대로 되게 하려면 문제 유형에 맞게 적절한 Loss를 지정해줘야한다. 즉, 분류에 적합한 Loss는 categorical_crossentropy이다.

### hidden layer

지금 까지는 하나의 퍼셉트론을 가지고 진행했다. 그러나 더 복잡한 딥러닝 모델을 만들기 위해서는 여러개의 퍼셉트론이 합쳐져서 모델을 만들어야한다. 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e0d994a9-639f-4f58-bf29-45b206960152/Untitled.png)

기존 퍼셉트론을 보면 input, output layer가 존재한다. 여기에 hidden layer라는 계층을 추가하게 되는데 

input - hidden, hidden - output 을 나눠서 보게 되면 2개의 퍼셉트론으로 구성되어 있다는 것을 알 수 있다.

![스크린샷 2022-05-04 오전 1.23.28.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2fc5ebcd-8c2e-47d4-9e51-225317e0920f/스크린샷_2022-05-04_오전_1.23.28.png)

### 데이터 타입 조정

![스크린샷 2022-05-04 오전 1.34.49.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/727106ee-b6ed-4bd3-92e9-239505e5c7b5/스크린샷_2022-05-04_오전_1.34.49.png)

품종을 숫자로 변환한 경우 그러나 csv에서는 그냥 숫자로 인식하기 때문에 원핫인코딩이 안됨.

DataFrame.dtypes() → 컬럼 타입 체크

category이거나 object type인 경우에만 원핫인코딩이 가능하다.

아이리스[’품종'] = 아이리스[’품종'].astype(’category’) 처럼 타입을 변경한다.

![스크린샷 2022-05-04 오전 1.38.06.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8b747d77-79ea-4ad5-9144-72d3e8d07e02/스크린샷_2022-05-04_오전_1.38.06.png)

아이리스.isna().sum() → NaN가 있는지 확인

mean = 아이리스[’꽃잎폭'].mean() → 평균을 구한다.

아이리스[’꽃잎폭'] = 아이리스[’꽃잎폭'].fillna(mean) → 평균값을 NaN에 채운다.

### 학습이 잘 되는 모델

- BatchNormalization