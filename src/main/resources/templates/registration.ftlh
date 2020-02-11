<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<!--Если есть такое поле message мы его показываем. Поле берем из RegistrationСontroller-->
<@c.page>
    <div class="mb-1">Add new user</div>
    ${message?if_exists}
<@l.login "/registration" true/>
</@c.page>