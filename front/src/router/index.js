import { createWebHistory, createRouter } from "vue-router";
import Home from "../views/Home_h.vue";
import PostDetail from "../views/PostDetail.vue";
import MainCalendar from "../views/MainCalendar.vue";
import BoardMain from "../views/BoardMain.vue";
import BoardDetail from "../views/BoardDetail.vue";

const routes = [
  {
    path: "/",
    name: "Post List",
    component: Home,
  },
  {
    path: "/about",
    name: "PostDetail",
    component: PostDetail,
  },
  {
    path: "/MainCalendar",
    name: "MainCalendar",
    component: MainCalendar,
  },
  {
    path: "/Board",
    name: "Board",
    component: BoardMain,
    children:[
      {path : ':id', name: BoardDetail, component: BoardDetail}
    ],
  },
  // {
  //   path: "/boardDetail",
  //   name: "boardDetail",

  //   component: boardDetail,
  // },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;