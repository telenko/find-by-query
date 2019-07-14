### FindByQuery
FindByQuery is an extended version of @FindBy annotation for selenium engine. Difference is only in support of [xdompath](https://github.com/telenko/xdompath "xdompath") query language in addition to all defaults. (XDomPath is query language similar to XPATH but **with ShadowDOM support**)

### API
1) Install (**not supported for now as public maven dependency!**)
Maven dependency:
```XML
<dependency>
    <groupId>telenko</groupId>
    <artifactId>find-by-query</artifactId>
    <version>0.0.1-beta</version>
    <type>jar</type>
    <scope>test</scope>
</dependency>
```
2) Usage
```Java
class SomePage {
    @FindByQuery(xdompath = ".//div")
    WebElement div;

    @FindByQuery(className = "header")
    WebElement header;
    ...
}
```