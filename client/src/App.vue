<template>
    <div id="app" class="app">
        <nav id="nav">
            <router-link to="/">Home</router-link>
            |
            <router-link to="/about">About</router-link>
        </nav>
        <router-view/>
        <Footer/>
    </div>
</template>
<script>

</script>
<style lang="scss">
    * {
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    body, html {
        height: 100%;
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
    }

    #app {
        color: #2c3e50;
        display: flex;
        flex-flow: row wrap;

        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        font-smoothing: antialiased;
        text-align: center;
    }

    #nav {
        background-color: tomato;
        flex-grow: 2;
        height: 5em;
        margin-bottom: 2em;
        padding: 30px;
        text-align: center;
        width: 100%;

        a {
            font-weight: bold;
            color: #2c3e50;
            &.router-link-exact-active {
                color: white;
            }
        }
    }

    .home {
        text-align: left;
        margin: 0 auto;
    }

</style>
