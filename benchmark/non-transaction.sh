#!/bin/bash

disable_node_start=$(date +%s)

for i in {1..117} 
do 
  curl -s -k -u "admin:F5survive@123" https://192.168.10.44/mgmt/tm/ltm/node/10.1.20.$i -X PATCH -d '{"session": "user-disabled"}' -H "Content-type: application/json" -o /dev/null
done

disable_node_end=$(date +%s)

echo "disable 117 nodes spend $(($disable_node_end - $disable_node_start)) seconds"

extract_member_start=$(date +%s)

for i in {0..49}
do
  pool="pool_$((i * 20))"
  curl -s -k -u "admin:F5survive@123" https://192.168.10.44/mgmt/tm/ltm/pool/$pool/members -o /dev/null
done

extract_member_end=$(date +%s)

echo "extract 1050 members status spend  $(($extract_member_end - $extract_member_start)) seconds"
echo "disable 117 nodes and extract 1050 member status spend $(($extract_member_end - $disable_node_start)) seconds"

enable_node_start=$(date +%s)

for i in {1..117}
do
  curl -s -k -u "admin:F5survive@123" https://192.168.10.44/mgmt/tm/ltm/node/10.1.20.$i -X PATCH -d '{"session": "user-enabled"}' -H "Content-type: application/json" -o /dev/null
done

enable_node_end=$(date +%s)

echo "enable 117 nodes spend $(($enable_node_end - $enable_node_start)) seconds"

extract_member_enable_start=$(date +%s)

for i in {0..49}
do
  pool="pool_$((i * 20))"
  curl -s -k -u "admin:F5survive@123" https://192.168.10.44/mgmt/tm/ltm/pool/$pool/members -o /dev/null
done

extract_member_enable_end=$(date +%s)

echo "extract 1050 members status spend  $(($extract_member_enable_end - $extract_member_enable_start)) seconds"
echo "enable 117 nodes and extract 1050 member status spend $(($extract_member_enable_end - $enable_node_start)) seconds"
echo 
