reset
set autoscale xfix
set autoscale yfix
set yrange [] reverse 
set cbrange[0:255]
set size square 1,1
set palette grey 
set datafile separator comma
plot filename matrix with image 
pause 5
#reread