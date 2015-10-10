library(ggplot2)
stats <- read.csv("./data/lab1.csv")
counts <- as.numeric(stats$count)
temp <- table(as.vector(counts))

avrg <- mean(counts)
mode <- names(temp)[temp == max(temp)]
mdn <- median(counts)
dsp <- var(counts)
acf(counts)

ggplot(data=stats, aes(x=year, y=count)) + geom_line() + geom_point()
