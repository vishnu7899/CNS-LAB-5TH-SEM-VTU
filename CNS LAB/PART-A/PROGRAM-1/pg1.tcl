set ns [new Simulator]    				
set nf [open pg1.nam w]  				
$ns namtrace-all $nf       				
set tf [open pg1.tr w]   				
$ns trace-all $tf

proc finish { } {     					 
global ns nf tf
$ns flush-trace     				 
close $nf
close $tf
exec nam pg1.nam & 
exit 0
}
#creating nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

#linking nodes
$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 512Kb 10ms DropTail

#setting queue size of the link
$ns queue-limit $n0 $n1 10

#creating a udp connection in network simulator
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0

#set up CBR over udp
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.005
$cbr0 attach-agent $udp0
set sink [new Agent/Null]
$ns attach-agent $n2 $sink
$ns connect $udp0 $sink

#scheduling events
$ns at 0.2 "$cbr0 start"
$ns at 4.5 "$cbr0 stop"
$ns at 5.0 "finish"
$ns run
