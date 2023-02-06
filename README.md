# Protobuf example

```shell
protoc -I=src/main/resources/protobuf/ --java_out=src/main/java src/main/resources/protobuf/addressbook.proto
```

Based on this: https://www.baeldung.com/google-protocol-buffer