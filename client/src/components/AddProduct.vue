<template>
    <section>
        <div id="main" class="container">
            <div id="heading" class="alert alert-success">
                <h2>Add New Prodict</h2>
                <div id="fdescription" class="notranslate 0">Fill in new product details in the field below.</div>
            </div>
            <form @submit="addProduct">

                <ul id="formFields" class="">
                    <li class="form-group row">
                        <label class="desc" id="title1" for="name">Description<span class="req"
                                                                                    id="req_1"> *</span></label>
                        <div>
                            <input id="name" name="name" v-model="name" type="text" class="field text large" value=""
                                   placeholder="Name of Product"
                                   tabindex="0"/>

                        </div>

                    </li>
                    <li class="form-group row">
                        <label class="desc " id="title3" for="items">Number of Items<span class="req"
                                                                                          id="req_3"> *</span></label>
                        <div>
                            <input id="items" name="items" v-model="items" type="number"
                                   placeholder="Count"
                                   class="field text small"/>
                        </div>

                    </li>
                    <li class="form-group row">
                        <label class="desc">Price<span class="req" id="req_6"> *</span></label>
                        <div>
                            <span>
                                <input min="0.01" max="200.00" name="price" type="number" step="0.01" v-model="price"
                                       placeholder="0.00"
                                       class="field text currency small"
                                       />

                            </span>
                        </div>

                    </li>
                    <li class="form-group row">
                        <label class="desc " id="title8" for="weight">Size<span class="req" id="req_8"> *</span></label>
                        <div>
                            <input id="weight" name="weight" type="text" v-model="weight"
                                   class="field text nospin small"/>
                        </div>
                    </li>
                    <li class="form-group row">
                        <label class="desc" id="title9" for="pictureUrl">Picture<span class="req"
                                                                                      ></span></label>
                        <div>
                            <input id="pictureUrl" name="pictureUrl" type="file"
                                   class="field text large"
                                   accept="image/*"/>
                        </div>

                        <p class="instruct hide" id="instruct9">
                            <small></small>
                        </p>


                    </li>
                </ul>
                <input type="submit" value="Save Product" class="btn btn-primary">
            </form>
            {{output}}
        </div>
    </section>
</template>
<script>
    const BASE_URL = 'http://localhost:8084/api/products';
    import axios from 'axios'

    export default {
        name: "AddProduct",
        data() {
            return {
                name: '',
                price: '',
                weight: '',
                items: '',
                pictureUrl: '',
                output: '',
            }
        },
        methods: {
            addProduct(e) {
                e.preventDefault();
                let currentObj = this;
                axios.post(BASE_URL, {
                    name: this.name,
                    price: this.price,
                    weight: this.weight,
                    items: this.items,
                    pictureUrl: this.pictureUrl,
                }).then(response => {
                        currentObj.output = response.data;
                        this.output = response.data;
                        this.$emit('add-product', response.data);
                    }
                ).catch(err => {
                    currentObj.output = err;
                    this.output = err;
                });

            }
            ,
            upload(e) {
                let files = e.target.files || e.dataTransfer.files;
                if (!files.length)
                    return;
            }
        }
    }
</script>
<style>
    ul {
        list-style-type: none;
    }

    input.text {
        border: 1px solid #c3c3c3;
        border-top-color: #7c7c7c;
        border-bottom-color: #ddd;
        background: #fff;
    }

    .text {
        font-family: "Lucida Grande", Tahoma, Arial, sans-serif;
        font-size: 100%;
        color: #333;
        margin: 0;

    }

    .desc {
        font-size: 95%;
        font-weight: bold;
        color: #222;
        line-height: 150%;
        margin: 0;
        padding: 0 0 3px 0;
        border: none;
        display: block;
        white-space: normal;
        width: 100%;
    }

    span.req {
        display: inline;
        float: none;
        color: red !important;
        font-weight: bold;
        margin: 0;
        padding: 0;
    }

    input.small, select.small {
        width: 25%;
    }

    .medium {
        width: 50%;
    }

    .large {
        width: 100%;
    }
    input[type=number]::-webkit-inner-spin-button,
    input[type=number]::-webkit-outer-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    input[type=number] {
        -moz-appearance:textfield; /* Firefox */
    }
    .desc {
        /*float:left;*/
        /*width:31%;*/
        /*margin:0 15px 0 0;*/
    }
    #heading{
        text-align: center;
    }
</style>