import paramiko
import getpass
import time

start = time.time()
cli = paramiko.SSHClient()
cli.set_missing_host_key_policy(paramiko.AutoAddPolicy)


test = ['10.231.9.19','10.231.9.20','10.231.9.59','10.231.9.60']


cli.connect('10.238.184.104', port=22, username='root', password='Ldcccld2020!')
channel = cli.invoke_shell()

for item in test:
    #print(item)

    stdin, stdout, stderr = cli.exec_command("ssh cloudadmin@"+item)
    
    
    data =stdout.readlines()
    #stdin.write('Ldcccld2020!\n')
    #stdin.flush()


##data =stdout.read()
#lines = stdout.write('Ldcccld2020!') #행위가 없으면 돌지 않는듯 
#print(''.join(data))


    if(time.time()-start <1.00):
        print(item +" : 미삭제")
        start = time.time()
    else:
        print(item," : 삭제")
        start = time.time()


