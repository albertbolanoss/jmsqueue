# Java Message Service Basic Example

## Pre requirements
- Download [ActiveMQ](https://activemq.apache.org/components/classic/download/)
- Unzip this one in a specify folder
- Add to the Path environment variable the specify-folder/bin 

## Run ActiveMQ
Execute un CMD the following command

```sh
activemq start
```

## Verify port 61616

- Verify if it listens up the port 61616, then it must have been listening on port 61616.

For windows: 

```sh
netstat -an | findstr 61616
```

For linux:

```sh
telnet localhost 61616
```

Note: If you can see the listeners of the port, verify the firewall input and output rules


## Access to ActiveMQ Console

- Access to http://localhost:8161/admin

# Run



