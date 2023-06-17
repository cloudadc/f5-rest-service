#!/usr/bin/python3

def generate_pool_tmsh(name, members):
    pool = "tmsh create ltm pool " + name + " members add {"
    for member in members:
        pool += " "
        pool += member
    pool += " } "
    print(pool)
    dest = members[0].replace("10.1.20", "10.1.10")
    vip = dest.split(':')[0] 
    vport = dest.split(':')[1] 
    vs_name = "vs_" + vip + "_" + vport
    vs = "tmsh create ltm virtual " + vs_name + " destination " + vip + ":" + vport + " pool " + name + " ip-protocol tcp profiles add { http { } }"
    print(vs)

def generate(members):
    for i in range(0, len(members), 20):
        name = "pool_" + str(i)
        if i >= 980 :
            generate_pool_tmsh(name, members[i:len(members) - 1])
            break
        else:
            generate_pool_tmsh(name, members[i:i+20])

with open("members.txt", "r") as file:
    members = []
    for line in file:
        line = line.replace("\n", "")
        if not line.startswith("10.1.20.118") and not line.startswith("10.1.20.119") and not line.startswith("10.1.20.120") :
            members.append(line)
    generate(members)


