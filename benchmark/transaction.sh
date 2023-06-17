#!/bin/bash

for i in {0..49}
do 
  pool="pool_$((i * 20))"
  curl -s -k -u "admin:F5survive@123" https://192.168.10.44/mgmt/tm/ltm/pool/$pool/members -o /dev/null
done
