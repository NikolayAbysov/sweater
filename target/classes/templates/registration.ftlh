<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<!--Если есть такое поле message мы его показываем. Поле берем из RegistrationСontroller-->
<@c.page>
Add new user
    ${message}
<@l.login "/registration" />
</@c.page>