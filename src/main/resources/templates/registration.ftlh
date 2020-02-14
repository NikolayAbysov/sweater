<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<!--Если есть такое поле message мы его показываем. Поле берем из RegistrationСontroller-->

<@c.page>
    <div class="mb-1">Add new user</div>
    ${message?ifExists}
    <@l.login "/registration" true />
</@c.page>