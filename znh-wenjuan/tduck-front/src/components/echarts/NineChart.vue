<template>
    <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
const echarts = require('echarts')
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

export default {
    mixins: [resize],
    props: {
        className: {
            type: String,
            default: 'chart'
        },
        width: {
            type: String,
            default: '90%'
        },
        height: {
            type: String,
            default: '250px'
        },
        autoResize: {
            type: Boolean,
            default: true
        },
        chartOption: {
            type: Array,
            required: true
        }
    },
    data() {
        return {
            chart: null
        }
    },
    watch: {
        chartOption: {
            deep: true,
            handler(val) {
                this.setOptions(val)
            }
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.initChart()
        })
    },
    beforeDestroy() {
        if (!this.chart) {
            return
        }
        this.chart.dispose()
        this.chart = null
    },
    methods: {
        initChart() {
            this.chart = echarts.init(this.$el, 'macarons')
            this.setOptions(this.chartOption)
            this.chart.resize()
        },
        setOptions(chartOption) {
            console.log(chartOption)
            // this.chart.setOption(chartOption)
            this.chart.setOption({
                radar: {
                    // shape: 'circle',
                    indicator: [
                        { name: '1型完美型', max: 10 },
                        { name: '2型助人型', max: 10 },
                        { name: '3型成就型', max: 10 },
                        { name: '4型自我型', max: 10 },
                        { name: '5型理智型', max: 10 },
                        { name: '6型怀疑型', max: 10 },
                        { name: '7型活跃型', max: 10 },
                        { name: '8型领袖型', max: 10 },
                        { name: '9型和平型', max: 10 }
                    ]
                },
                series: [
                    {
                        type: 'radar',
                        data: [
                            {
                                // value: chartOption
                                value: chartOption
                            }
                        ]
                    }
                ]
            })
        }
    }
}
</script>
