<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">编辑客户跟踪</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <FormItem label="客户" prop="customerId">
                <Select v-model="form.customerId" clearable placeholder="请选择跟进的客户..." style="width:570px">
                    <Option v-for="(item,index) in customerList" :key="index" :value="item.id">{{ item.title }}</Option>
                </Select>
            </FormItem>
            <FormItem label="跟进日期" prop="date">
                <Input v-model="form.date" readonly style="width:570px" />
            </FormItem>
            <FormItem label="跟进内容" prop="content">
                <Input v-model="form.content" clearable type="textarea" :rows="4" show-word-limit maxlength="240" placeholder="请输入客户的跟进内容..." style="width:570px" />
            </FormItem>
            <FormItem label="跟进人" prop="userName">
                <Input v-model="form.userName" readonly style="width:570px" />
            </FormItem>
            <Form-item class="br">
                <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                <Button @click="handleReset">重置</Button>
                <Button type="dashed" @click="close">关闭</Button>
            </Form-item>
        </Form>
    </Card>
</div>
</template>

<script>
import {
    editCustomerItem,
    getCustomerList
} from "./api.js";
export default {
    name: "edit",
    components: {},
    props: {
        data: Object
    },
    data() {
        return {
            submitLoading: false, // 表单提交状态
            form: { // 添加或编辑表单对象初始化数据
                customerId: "",
                customerName: "",
                date: "",
                content: "",
                userId: "",
                userName: "",
            },
            // 表单验证规则
            formValidate: {},
            customerList: []
        };
    },
    methods: {
        init() {
            this.getCustomerListFx();
            this.handleReset();
            this.form = this.data;
        },
        getCustomerListFx() {
            var that = this;
            that.customerList = [];
            getCustomerList().then(res => {
                if (res.success) {
                    that.customerList = res.result;
                }
            })
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    editCustomerItem(this.form).then(res => {
                        this.submitLoading = false;
                        if (res.success) {
                            this.$Message.success("操作成功");
                            this.submited();
                        }
                    });
                }
            });
        },
        close() {
            this.$emit("close", true);
        },
        submited() {
            this.$emit("submited", true);
        }
    },
    mounted() {
        this.init();
    }
};
</script>

<style lang="less">
// 建议引入通用样式 具体路径自行修改 可删除下面样式代码
// @import "../../../styles/single-common.less";
.edit-head {
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;

    .back-title {
        color: #515a6e;
        display: flex;
        align-items: center;
    }

    .head-name {
        display: inline-block;
        height: 20px;
        line-height: 20px;
        font-size: 16px;
        color: #17233d;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .window-close {
        z-index: 1;
        font-size: 12px;
        position: absolute;
        right: 0px;
        top: -5px;
        overflow: hidden;
        cursor: pointer;

        .ivu-icon-ios-close {
            color: #999;
            transition: color .2s ease;
        }
    }

    .window-close .ivu-icon-ios-close:hover {
        color: #444;
    }
}
</style>
