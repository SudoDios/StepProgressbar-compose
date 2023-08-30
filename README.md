# StepProgressbar-compose
make your step progress bar (like instagram stroty indicator) easily on your project (compose)

<img src="https://github.com/SudoDios/StepProgressbar/blob/master/app/src/main/ic_launcher-playstore.png" alt="drawing" width="160"/>

### Usage

> #### Circle
> ```kotlin
> StepProgressBar(
>            size = 150.dp,
>            progressWidth = 7.dp,
>            steps = steps,
>            progressBackgroundWidth = 7.dp,
>            progressColors = Pair(Color(0xFFCD7672), Color(0xFFEEB462)),
>            gradientDegree = 145.0,
>            progress = Pair(currentStep,progress),
>            progressBackgroundColor = Color.White.copy(0.2f)
>  )
> ```

> #### Line
> ```kotlin
> LineStepProgressBar(
>            modifier = Modifier,
>            spaceSize = 12.dp,
>            progressWidth = 7.dp,
>            steps = steps,
>            progressBackgroundWidth = 7.dp,
>            progressColors = Pair(Color(0xFFCD7672), Color(0xFFEEB462)),
>            progress = Pair(currentStep,progress),
>            progressBackgroundColor = Color.White.copy(0.2f)
> )
> ```
