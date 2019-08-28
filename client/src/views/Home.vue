<template>
    <div class="home">
        <Header/>
        <Products/>

    </div>

</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import Header from '@/components/layout/Header.vue';
    import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
    import Products from '@/components/Products.vue';
    import Footer from '@/components/layout/Footer.vue';
    import BootstrapVue from 'bootstrap-vue';

    Vue.use(BootstrapVue);
    @Component({
        components: {
            Header,
            HelloWorld,
            Products,
            Footer,
        },
    })
    export default class Home extends Vue {
    }
</script>

<style>

</style>
