# JSP Template Inheritance
The upgraded version of this repository: [kwon37xi/jsp-template-inheritance](https://github.com/kwon37xi/jsp-template-inheritance)

This version compatible with JDK 11.

[![](https://img.shields.io/badge/1.0_Version-%2312100E.svg?logo=github&logoColor=white)](https://github.com/KusalAdithya/jsp-template-inheritance)

With these template inheritance tag library you can manage template layouts.

You don't need to use layout frameworks like [Sitemesh](http://wiki.sitemesh.org/display/sitemesh/Home) or
[Apache Tiles](http://tiles.apache.org/).

If you use freemarker, refer to [freemarker-template-inheritance](https://github.com/kwon37xi/freemarker-template-inheritance).

### Maven dependencies

```xml
<dependency>
    <groupId>com.waka.template</groupId>
    <artifactId>jsp-template</artifactId>
    <version>1.0</version>
</dependency>
```

### Gradle dependencies

```groovy
dependencies {
    implementation 'com.waka.template:jsp-template:1.0'
}
```

## Usage
### web.xml
```xml
<!-- jsp-inheritance-prefix, jsp-inheritance-suffix can be omitted.-->

<context-param>
    <param-name>jsp-inheritance-prefix</param-name>
    <param-value>/WEB-INF/layouts/</param-value>
</context-param>

<context-param>
    <param-name>jsp-inheritance-suffix</param-name>
    <param-value>.jsp</param-value>
</context-param>
```

### /WEB-INF/layouts/base.jsp : layout

```jsp
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://com.waka.template/jsp-template" prefix="layout" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>JSP Template Inheritance</title>
    </head>

<h1>Head</h1>
<div>
    <layout:block name="header">
        header
    </layout:block>
</div>

<h1>Contents</h1>
<div>
    <p>
    <layout:block name="contents">
        <h2>Contents will be placed under this h2</h2>
    </layout:block>
    </p>
</div>

<div class="footer">
    <hr />
    <a https://github.com/KusalAdithya/jsp-template">jsp template inheritance example</a>
</div>
</html>
```

### view.jsp : contents

```jsp
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://com.waka.template/jsp-template" prefix="layout" %>

<layout:extends name="base">
    <layout:put block="header" type="REPLACE">
        <h2>This is an example about layout management with JSP Template Inheritance</h2>
    </layout:put>
    <layout:put block="contents">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin porta,
        augue ut ornare sagittis, diam libero facilisis augue, quis accumsan enim velit a mauris.
        Ut eleifend elit ante, sit amet suscipit leo lobortis eu. Quisque vitae lorem feugiat,
        lacinia nulla eu, interdum eros. Ut dignissim tincidunt nisl ac iaculis.
        Praesent consectetur arcu vitae tellus scelerisque venenatis.
        Morbi vel leo eros. In id libero ultricies, laoreet enim et, tempor magna.
        Vestibulum lorem velit, accumsan id purus at, lobortis fermentum diam.
        Aenean nec placerat elit. Aenean vel sem arcu.
    </layout:put>
</layout:extends>
```
### PutType
1. APPEND : The put contents will be appended after block's contents. default.
1. PREPEND : The put contents will be prepended before block's contents.
1. REPLACE : The put contents will replace block's contents. The block's contents will be removed.

PutType vlaues are case insensitive.

### Layout path prefix/suffix

In web.xml you can set `jsp-inheritance-prefix` and `jsp-inheritance-suffix` context parameters.
The layout name will be prefixed and suffixed with these parameters.

When you set `jsp-inheritance-prefix` as `/WEB-INF/layouts/`, `jsp-ineheritance-suffix` as
`.jsp` and call `<layout:extends name='base'>` the layout file URL will be `/WEB-INF/layouts/base.jsp`.

The prefix and suffix can be omitted.

### Example
`example` module is web application layout example. Run the module and browse <http://localhost:8080/index.jsp>.
Refer to `index.jsp`, `WEB-INF/layouts/base.jsp`, `WEB-INF/layouts/secondLayout.jsp`.

## Known problems

If you want to pass a variable from a child page to it's layout page, the variable must be added to **REQUEST scope**.

## Reference

1. [freemarker-template-inheritance](https://github.com/kwon37xi/freemarker-template-inheritance)
2. [jsp-template-inheritance](https://github.com/kwon37xi/jsp-template-inheritance)
