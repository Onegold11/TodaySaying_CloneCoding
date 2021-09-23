## TodaySaying_CloneCoding
Firebase의 Remote Config 기능을 이용한 오늘의 명언을 제공하는 앱

## 사용한 요소
- UI
  - ViewPager2
  - StatusBar Theme
- Remote Config

## ViewPager2
[공식 문서](https://developer.android.com/jetpack/androidx/releases/viewpager2?hl=ko)

[개발자 가이드](https://developer.android.com/training/animation/vp2-migration?hl=ko)

컨텐츠를 옆으로 넘길 수 있는 뷰
- RecycleyView와 동일한 어댑터 클래스를 사용한다.

```kotlin
this.viewPager.setPageTransformer { page, position ->

            if (position.absoluteValue >= 1.0F) {
                page.alpha = 0F
            } else if (position == 0F) {
                page.alpha = 1F
            } else {
                page.alpha = 1F - 2 * position.absoluteValue
            }
        }
```
- ViewPager의 setPageTransformer 메소드를 사용하면 컨텐츠의 위치에 따라 다양한 효과를 줄 수 있다.
- 마지막에 2를 곱한 것은 투명 효과를 좀 더 강조하기 위해서

```kotlin
viewPager.setCurrentItem(adapter.itemCount / 2, false)
```
- setCurrentItem 메소드를 사용하면 시작 위치를 지정할 수 있다.

```kotlin
override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {

        val actualPosition = position % quotes.size
        holder.bind(quotes[actualPosition], isNameRevealed)
    }

    override fun getItemCount() = Int.MAX_VALUE
```
- 어댑터 클래스에서 getItemCount를 매우 큰 수로 놓으면 컨텐츠가 순환하는 것처럼 보이게 만들 수 있다.
- 이때 onBindViewHolder에서 실제 컨텐츠 순서를 따로 지정해줘야 한다.

## StatusBar Theme
- android:statusBarColor: 상태창 배경 색상
- android:windowLightStatusbar: 상태창 글씨 색상, true(흰색), false(검은색)

## Remote Config
[공식 문서](https://firebase.google.com/docs/remote-config)
앱의 업데이트 없이 다양한 데이터를 받을 수 있는 기능
- 전달받은 데이터를 사용해 컨텐츠, UI 등을 변경할 수 있다.

```kotlin
val remoteConfig = Firebase.remoteConfig

        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
        )
```
- Remote Config는 호출 횟수에 제한이 있으므로 업데이트 시간 간격을 설정할 수 있다.

```kotlin
remoteConfig.fetchAndActivate().addOnCompleteListener {

            this.progressBar.visibility = View.GONE

            if (it.isSuccessful) {
                remoteConfig.getString("quotes"))
                remoteConfig.getBoolean("is_name_reveal")
            }
        }
```
- Remote Config에서 데이터를 성공적으로 전달받은 경우, 데이터를 사용할 수 있다.
- 전달받은 데이터는 Json, Boolean, String 등이 있으며 Firebase의 Remote Config 메뉴에서 확인할 수 있다.
- Json 데이터를 사용할 때는 Gson 라이브러리를 사용하는 것을 
