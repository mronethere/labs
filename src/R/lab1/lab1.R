library(ggplot2)
myData <- read.csv("lab1.csv")
ggplot(data=myData, aes(x=year, y=count)) + geom_line() + geom_point()