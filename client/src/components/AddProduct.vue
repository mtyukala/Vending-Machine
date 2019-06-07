<template>
    <section>
        <div id="main" class="container">
            <form @submit="addProduct">
                <div id="wuform" class="" title="Click to edit.">

                    <h2 id="fname" class="notranslate 0">Add New Prodict</h2>
                    <div id="fdescription" class="notranslate 0">Fill in new product details in the field below.</div>
                </div>
                <ul id="formFields" class="">
                    <li>
                        <label class="desc" id="title1" for="name">Description<span class="req"
                                                                                    id="req_1"> *</span></label>
                        <div>
                            <input id="name" name="name" v-model="name" type="text" class="field text medium" value="" maxlength="50"
                                   tabindex="0"/>
                        </div>

                    </li>
                    <li>
                        <label class="desc " id="title3" for="items">Number of Items<span class="req"
                                                                                          id="req_3"> *</span></label>
                        <div>
                            <input id="items" name="items" v-model="items"" type="number" class="field text nospin small"/>
                        </div>

                    </li>
                    <li>
                        <label class="desc" id="title6">Price<span class="req" id="req_6"> *</span></label>
                        <span class="symbol">R</span>
                        <span>
		<input id="price" name="price" type="number" v-model="price" class="field text currency nospin" value="" size="10"/>
		<label for="price">Rand</label>
	</span>

                    </li>
                    <li>
                        <label class="desc " id="title8" for="weight">Size<span class="req" id="req_8"> *</span></label>
                        <div>
                            <input id="weight" name="weight" type="text" v-model="weight" class="field text nospin small"/>
                        </div>
                    </li>
                    <li>
                        <label class="desc" id="title9" for="pictureUrl">Picture<span class="req"
                                                                                      id="req_9"></span></label>
                        <div>
                            <input id="pictureUrl" name="pictureUrl" type="file" class="field text medium"
                                   accept="image/*"/>
                        </div>

                        <p class="instruct hide" id="instruct9">
                            <small></small>
                        </p>


                    </li>
                </ul>
                <input type="submit" value="Save Product" class="btn">
            </form>

        </div>
    </section>
</template>
<script>
    export default {
        name: "AddProduct",
        data(){
            return {
                name:'',
                price:'',
                weight:'',
                items:'',
                prictureUrl:
                ''
            ,}
        },
        methods:{
            addProduct(e){
                e.preventDefault();
                const newProduct={
                    name:this.name,
                    price:this.price,
                    weight:this.weight,
                    items:this.items,
                    pictureUrl:this.pictureUrl,
                }
                this.$emit('add-product', newProduct);
                this.name='';
                this.price='';
                this.weight='';
                this.items=''                ;
                this.pictureUrl='';
            }
        }
    }
</script>
<style>
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
        padding: 2px 0;
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

    .medium {
        width: 100%;
    }

    .desc{
        float:left;
        width:31%;
        margin:0 15px 0 0;
    }
</style>