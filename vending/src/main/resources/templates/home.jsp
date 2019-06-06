<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Vending Machine Application">
    <meta name="author" content="Mkhululi Tyukala">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet"/>
    <title>Home Page</title></head>
<body>
<div class="container">
    <div id="products" class="col-sm-9 ">
        <ul>
            <li style="list-style-type:none" v-for="product in products">
                <player-card
                        v-bind:product="product" v-bind:key="product.id">
                </player-card>
            </li>
        </ul>
    </div>
    <div class="lead">
        <strong>
            <span th:text="${appName}"></span>
        </strong>
    </div>
    <section th:each="p : ${products}">
        <div class="col-sm-4">
            <div class="panel panel-default">
                <a style="text-decoration:none" th:href="@{/api/products/{id}(id=${p.Id})}">
                    <span class="panel-body">
                        <span th:text="${p.name}"/></span>&nbsp; (x<span th:text="${p.items}"></span>)
                    <p th:text="${p.price}"/></p>
                    <p th:text="${p.weight}"/></p>

                </a>
            </div>
        </div>
    </section>

</div>

<script
        src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js">
</script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.21.1/babel.min.js">
</script>
<script type="text/babel">


    Vue.component('products',
        props
    :
    ['product'],
        template
    :
    `<div class="card">
<div class="card-body">
<h6 class="card-title">
{{product.name}}
</h6>
<p class="card-text"><div>{{product.price}}</div>
</p>
</div>
</div>`
    )
    ;
    var app = new Vue({
        el: '#products',
        data: {
            products: [
                {id: "1", name: "Coke", "price": "9.99", "items": "10", "weight": "0.5"},
                {id: "2", name: "Fanta", "price": "9.99", "items": "10", "weight": "0.5"},
                {id: "3", name: "Simba Chips", "price": "5.99", "items": "15", "weight": "0.2"}
            ]
        }
    });
</script>
</body>
</html>