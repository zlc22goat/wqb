
import VueRouter from 'vue-router';

const routes = [
    {
        path:'/',
        name:'login',
        component:()=>import('../components/Login')
    },
    {
        path:'/login',
        name:'login2',
        component:()=>import('../components/Login')
    },
    {
        path:'/register',
        name:'register',
        component:()=>import('../components/Register')
    },
    {
        path:'/Index',
        name:'index',
        component:()=>import('../components/Index'),
        children:[
            {
                path:'/Home',
                name:'home',
                meta:{
                    title:'首页'
                },
                component:()=>import('../components/Home')
            },
            {
                path:'/ReviewOption',
                name:'ReviewOption',
                component:()=>import('../components/question/ReviewOption')
            },
            {
                path:'/ReviewOther',
                name:'ReviewOther',
                component:()=>import('../components/question/ReviewOther')
            },
            {
                path:'/ReviewExamDetail',
                name:'ReviewExamDetail',
                component:()=>import('../components/exam/ReviewExamDetail')
            },
            {
                path:'/StartTest',
                name:'StartTest',
                component:()=>import('../components/exam/StartTest')
            },
            // {
            //     path:'/Admin',
            //     name:'admin',
            //     meta:{
            //         title:'管理员管理'
            //     },
            //     component:()=>import('../components/admin/AdminManage.vue')
            // },
            // {
            //     path:'/User',
            //     name:'user',
            //     meta:{
            //         title:'用户管理'
            //     },
            //     component:()=>import('../components/user/UserManage.vue')
            // },
        ]
    }
]

const router = new VueRouter({
    mode:'history',
    routes
})

export function resetRouter() {
    router.matcher = new VueRouter({
        mode:'history',
        routes: []
    }).matcher
}
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
export  default router;