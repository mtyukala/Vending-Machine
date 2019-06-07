<template>
    <section id="app">
        <HelloWorld msg="Welcome to Vue.js"/>
        <div class="card">
            <div class="card-body">
                <h6 class="card-title">
                    {{product.name}}
                </h6>
                <p class="card-text">
                <div>{{product.price}}</div>
                </p>
            </div>
        </div>
    </section>
</template>

<script>

    export default{
    name:'app',
    components:{
        HelloWorld
    }
}
</script>

<style>
#app{

    font-family: Arial, sans-serif;
    text-align: center;
    -moz-osx-font-smoothing: grayscale;
    -webkit-font-smoothing: antialiased;
    smoothing:antialised;
    color: #2c3e50;
    margin-top: 60px;
}


</style>