tmsh create ltm pool pool_1 members add { 10.1.20.11:8081 { address 10.1.20.11 } 10.1.20.12:8081 { address 10.1.20.12 } 10.1.20.23:8081 { address 10.1.20.23 } } monitor http
tmsh create ltm pool pool_2 members add { 10.1.20.13:8081 { address 10.1.20.13 } 10.1.20.14:8081 { address 10.1.20.14 } 10.1.20.24:8081 { address 10.1.20.24 }}
tmsh create ltm pool pool_3 members add { 10.1.20.15:8081 { address 10.1.20.15 } 10.1.20.16:8081 { address 10.1.20.16 } } monitor http
tmsh create ltm pool pool_4 members add { 10.1.20.17:8081 { address 10.1.20.17 } 10.1.20.18:8081 { address 10.1.20.18 } } monitor http
tmsh create ltm pool pool_5 members add { 10.1.20.19:8081 { address 10.1.20.19 } 10.1.20.20:8081 { address 10.1.20.20 } } monitor http
tmsh create ltm pool pool_6 members add { 10.1.20.21:8081 { address 10.1.20.21 } 10.1.20.22:8081 { address 10.1.20.22 } } monitor http
tmsh create ltm pool pool_7 members add { 10.1.20.11:8081 { address 10.1.20.11 } 10.1.20.12:8081 { address 10.1.20.12 } 10.1.20.13:8081 { address 10.1.20.13 } 10.1.20.14:8081 { address 10.1.20.14 } 10.1.20.15:8081 { address 10.1.20.15 } 10.1.20.16:8081 { address 10.1.20.16 } 10.1.20.17:8081 { address 10.1.20.17 } 10.1.20.18:8081 { address 10.1.20.18 } 10.1.20.19:8081 { address 10.1.20.19 } 10.1.20.20:8081 { address 10.1.20.20 }}
tmsh create ltm pool pool_8 members add { 10.1.20.11:8081 { address 10.1.20.11 } 10.1.20.12:8081 { address 10.1.20.12 } 10.1.20.13:8081 { address 10.1.20.13 } 10.1.20.14:8081 { address 10.1.20.14 } 10.1.20.15:8081 { address 10.1.20.15 } 10.1.20.16:8081 { address 10.1.20.16 } 10.1.20.17:8081 { address 10.1.20.17 } 10.1.20.18:8081 { address 10.1.20.18 } 10.1.20.19:8081 { address 10.1.20.19 } 10.1.20.20:8081 { address 10.1.20.20 }}
tmsh modify ltm node 10.1.20.12 monitor icmp
tmsh modify ltm node 10.1.20.21 monitor icmp
tmsh create ltm snatpool snat_1 { members add { 10.1.10.103} members add { 10.1.10.104} }
tmsh create ltm snatpool snat_2 { members add { 10.1.10.105} members add { 10.1.10.106} }
tmsh create ltm snatpool snat_3 { members add { 10.1.10.107} members add { 10.1.10.108} }
tmsh create ltm virtual vs_1 destination 10.1.10.11:80 pool pool_1 ip-protocol tcp profiles add { http { } } source-address-translation { type snat pool snat_1 }
tmsh create ltm virtual vs_2 destination 10.1.10.12:80 pool pool_2 ip-protocol tcp profiles add { http { } } source-address-translation { type snat pool snat_2 }
tmsh create ltm virtual vs_3 destination 10.1.10.13:80 pool pool_3 ip-protocol tcp profiles add { http { } } source-address-translation { type snat pool snat_3 }
tmsh create ltm virtual vs_4 destination 10.1.10.14:80 pool pool_4 ip-protocol tcp profiles add { http { } } source-address-translation { type automap }
tmsh create ltm virtual vs_5 destination 10.1.10.15:80 pool pool_5 ip-protocol tcp profiles add { http { } } source-address-translation { type automap }
tmsh create ltm virtual vs_6 destination 10.1.10.16:80 pool pool_6 ip-protocol tcp profiles add { http { } } source-address-translation { type automap }
