// main.js
import Vue from 'vue'
import Router from 'vue-router'
import Products from '@../components/Products'
import ProductPreview from '@../components/ProductPreview'
import Coin from '@../components/Coin'
import CoinPreview from '@../components/CoinPreview'

Vue.use(Router)
Vue.use(BootstrapVue)
export default new Router({
    routes: [
        {
            path: '/',
            name: 'Products',
            component: Products
        },
        {
            path: '/api/products/:id',
            name: 'Product Preview',
            component: ProductPreview
        }        ,
        {
            path: '/api/products/:id',
            name: 'coins',
            component: Coin
        }        ,
        {
            path: '/api/coins/:id',
            name: 'Coin Preview',
            component: CoinPreview
        }

    ]
})

new Vue({
    router, template:`
    <div>
      <nav class="navbar navbar-toggleable-md navbar-light bg-faded">
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item"><router-link to="/" class="nav-link">Home</router-link></li>
            <li class="nav-item"><router-link to="/about" class="nav-link">About</router-link></li>
            <li class="nav-item"><router-link to="/contact" class="nav-link">Contact</router-link></li>
          </ul>
        </div>
      </nav>
      <router-view class="view"></router-view>
    </div>
    `
}).$mount('#app')